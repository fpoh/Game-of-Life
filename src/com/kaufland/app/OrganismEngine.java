package com.kaufland.app;

public class OrganismEngine implements Runnable {

    private Organism organism;

    public OrganismEngine(Organism organism) {
        this.organism = organism;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            organism.cellCheck();
        }

    }
}
