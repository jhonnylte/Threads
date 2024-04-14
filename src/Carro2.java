import java.util.Objects;
import java.util.concurrent.Semaphore;

public class Carro2 {

    public static Semaphore semEsq = new Semaphore(0); // Esperando ou atrav na esquerda
    public static Semaphore semDir = new Semaphore(0); // Esperando ou atrav na direita
    public static Semaphore mutex = new Semaphore(1);
    public enum ponte {vazia, esquerda, direita}; // Sentido da ponte
    public static int numCarrosPonte;
    public static ponte statusPonte;
    static class Carro implements Runnable{
        public String origem;
        Carro(String origem){
            this.origem = origem;
        }

        public void atravessar(){
            System.out.println("Atravessando a partir do "+origem+"!");
            System.out.println("Atravessou!");
        }
        @Override
        public void run() {
            while (true){
                if (Objects.equals(this.origem, "oeste")){
                    try {
                        semEsq.release();
                        if(statusPonte == ponte.vazia){
                            semEsq.acquire();
                            mutex.acquire();
                            numCarrosPonte++;
                            statusPonte = ponte.direita;
                            mutex.release();
                            atravessar();
                            this.origem = "leste";
                        }


                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

                if (Objects.equals(this.origem, "leste")){
                    try {
                        semDir.release();
                        if(statusPonte == ponte.vazia){
                            semDir.acquire();
                            mutex.acquire();
                            numCarrosPonte++;
                            statusPonte = ponte.esquerda;
                            mutex.release();
                            atravessar();
                            this.origem = "oeste";
                        }

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }
    }

    public static void main(String[] args) {
        statusPonte = ponte.vazia;
        numCarrosPonte = 0;



    }
}

