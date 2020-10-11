/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import javax.swing.SwingWorker;

/**
 *
 * @author ast
 */
public class MCSimulationWorker extends SwingWorker<MCSimulationManager,Void>
{
    public boolean working = false;
    public boolean initialized=false;
    public int sampleSize = 0;
    public int workerNr=0;
    //private MorrisSampler ms;
    private MCSimulationManager mcm=null;
    
    
    public void setSimulationManager(MCSimulationManager mcm)
    {
        this.mcm=mcm;
    }
    
    @Override
    protected MCSimulationManager doInBackground() throws Exception 
    {
                working=true;
                //msm.setup();
                initialized=true;
                mcm.thread= workerNr;
                mcm.prefix="Thread "+workerNr+": ";
                mcm.simulationRuns=sampleSize;
                mcm.runMCSimulation();  
                //ms.saveResults(outputFolder);
                working=false;
                System.out.println("Background thread "+workerNr+ "is done. ");
                return mcm;
    }
    
  /*   @Override 
     protected void done()
     {
        System.out.println("Background thread is done.");
        working=false;
        try    
        {
           MCSimulationManager mcm = get();
                     
        }
        catch(Throwable e)
        {
             System.out.println("!!!!!!!!!!!!!! Error retrieving results from multi-threaded Monte Carlo Simulation.");
        }

      }*/
}
