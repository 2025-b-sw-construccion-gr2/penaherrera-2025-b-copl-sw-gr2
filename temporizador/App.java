package temporizador;

import temporizador.lib.Temporizador;
import temporizador.lib.Cronometro;

public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Demo Temporizador y Cronometro (biblioteca temporizador.lib.timer)");

		// Ejemplo: Temporizador de 5 segundos
		Temporizador t = new Temporizador(5);
		t.setListener(new Temporizador.Listener() {
			@Override
			public void onTick(long remainingSeconds) {
				System.out.println("Temporizador: quedan " + remainingSeconds + "s (" + t.getFormattedRemaining() + ")");
			}

			@Override
			public void onFinish() {
				System.out.println("Temporizador: ¡finalizado!");
			}
		});

		System.out.println("Iniciando temporizador 5s...");
		t.start();

		// Mientras el temporizador corre, demostramos el cronómetro
		Cronometro c = new Cronometro();
		c.start();

		// Esperar 6 segundos en el hilo principal (solo para demo)
		for (int i = 0; i < 6; i++) {
			Thread.sleep(1000);
			System.out.println("Cronometro (lap): " + c.lapFormatted());
		}

		c.stop();
		System.out.println("Cronometro final: " + c.lapFormatted());

		// limpiar recursos
		t.shutdown();
		System.out.println("Demo finalizada.");
	}
}
