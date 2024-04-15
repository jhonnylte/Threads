import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Carro2 {

    public static Semaphore semEsq = new Semaphore(0); // Esperando ou atrav na esquerda
    public static Semaphore semDir = new Semaphore(0); // Esperando ou atrav na direita
    public static Semaphore mutex = new Semaphore(1);

    public enum ponte {vazia, esquerda, direita}

    // Sentido da ponte
    public static int numCarrosPonte;
    public static ponte statusPonte;

    static class Carro implements Runnable {
        public String origem;

        Carro(String origem) {
            this.origem = origem;
        }

        public void atravessar() {
            System.out.println("Atravessando a partir do " + this.origem + "!");
            System.out.println("Atravessou!");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (Objects.equals(this.origem, "oeste")) {
                        semEsq.release();
                        if ((statusPonte == ponte.vazia) || (statusPonte == ponte.direita)) {
                            semEsq.acquire();
                            mutex.acquire();
                            numCarrosPonte++;
                            statusPonte = ponte.direita;
                            mutex.release();
                            atravessar();

                            mutex.acquire();
                            numCarrosPonte--;
                            if(numCarrosPonte == 0)
                                statusPonte = ponte.vazia;
                            mutex.release();

                            this.origem = "leste";
                        }
                    } else if (Objects.equals(this.origem, "leste")) {
                        semDir.release();
                        if ((statusPonte == ponte.vazia) || (statusPonte == ponte.esquerda)) {
                            semDir.acquire();

                            mutex.acquire();
                            numCarrosPonte++;
                            statusPonte = ponte.esquerda;
                            mutex.release();
                            atravessar();

                            mutex.acquire();
                            numCarrosPonte--;
                            if(numCarrosPonte == 0)
                                statusPonte = ponte.vazia;
                            mutex.release();

                            this.origem = "oeste";

                        }


                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }



    }

    public static void main(String[] args) {
        statusPonte = ponte.vazia;
        numCarrosPonte = 0;

        Carro car1 = new Carro("oeste");
        car1.run();
        Carro car2 = new Carro("leste");
        car2.run();
        Carro car3 = new Carro("oeste");
        car3.run();
    }
}


