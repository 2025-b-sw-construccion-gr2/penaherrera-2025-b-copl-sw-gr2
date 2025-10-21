package temporizador.lib;

/**
 * Cronometro simple (stopwatch) para usos educativos.
 */
public class Cronometro {
    private long startTime = 0;
    private long elapsed = 0;
    private boolean running = false;

    public void start() {
        if (running) return;
        running = true;
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        if (!running) return;
        elapsed += System.currentTimeMillis() - startTime;
        running = false;
    }

    public void reset() {
        startTime = 0;
        elapsed = 0;
        running = false;
    }

    public long lapMillis() {
        if (running) {
            return elapsed + (System.currentTimeMillis() - startTime);
        }
        return elapsed;
    }

    public String lapFormatted() {
        long ms = lapMillis();
        long s = ms / 1000;
        long msRem = ms % 1000;
        return String.format("%d.%03ds", s, msRem);
    }

    public boolean isRunning() {
        return running;
    }
}
