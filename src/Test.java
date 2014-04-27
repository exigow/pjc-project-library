
//

class Test extends Thread {
    private String napis;

    public Test(String napis) {
        this.napis = napis;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(napis);
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch(InterruptedException e) {
                //
            }
        }
    }
}