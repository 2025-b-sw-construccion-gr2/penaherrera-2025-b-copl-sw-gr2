package temporizador.lib;

import java.util.concurrent.*;

/**
 * Temporizador (countdown) simple para usos educativos.
 */
public class Temporizador {
    public interface Listener {
        void onTick(long remainingSeconds);
        void onFinish();
    }

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread th = new Thread(r, "Temporizador-Thread");
        th.setDaemon(true);
        return th;
    });

    private long initialSeconds;
    private long remainingSeconds;
    private volatile boolean running = false;
    private Listener listener;
    private ScheduledFuture<?> future;
    private final Object lock = new Object();

    public Temporizador(long seconds) {
        if (seconds < 0) seconds = 0;
        this.initialSeconds = seconds;
        this.remainingSeconds = seconds;
    }

    public void setListener(Listener l) {
        this.listener = l;
    }

    public void start() {
        synchronized (lock) {
            if (running) return;
            running = true;
            scheduleTick();
        }
    }

    private void scheduleTick() {
        future = scheduler.scheduleAtFixedRate(() -> tick(), 0, 1, TimeUnit.SECONDS);
    }

    private void tick() {
        synchronized (lock) {
            if (!running) return;
            if (remainingSeconds <= 0) {
                // finished
                running = false;
                if (future != null) future.cancel(false);
                if (listener != null) listener.onFinish();
            } else {
                remainingSeconds--;
                if (listener != null) listener.onTick(remainingSeconds);
            }
        }
    }

    public void pause() {
        synchronized (lock) {
            if (!running) return;
            running = false;
            if (future != null) future.cancel(false);
        }
    }

    public void resume() {
        synchronized (lock) {
            if (running) return;
            running = true;
            scheduleTick();
        }
    }

    public void reset() {
        synchronized (lock) {
            running = false;
            if (future != null) future.cancel(false);
            remainingSeconds = initialSeconds;
        }
    }

    /**
     * Stop and release resources. After calling shutdown the instance cannot be used.
     */
    public void shutdown() {
        synchronized (lock) {
            running = false;
            if (future != null) future.cancel(false);
            scheduler.shutdownNow();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public long getRemainingSeconds() {
        return remainingSeconds;
    }

    public String getFormattedRemaining() {
        long s = Math.max(0, remainingSeconds);
        long mins = s / 60;
        long secs = s % 60;
        return String.format("%d:%02d", mins, secs);
    }
}
