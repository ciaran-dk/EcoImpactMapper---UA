/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cumimpactsa;

import java.util.ArrayList;

/**
 * @summary Cuts the highest X% of unique values (only for data that are not presence-absence!)
 * @author ast
 */
public class PercentCutter extends GeneralProcessor
{

    public PercentCutter()
    {
        super();
        paramNames=new String[1];
        paramNames[0]="percent";
        paramValues=new double[1];
        paramValues[0]=1.0;
        
    }
    
    public void setPercent(double percent)
    {
        paramValues[0]=percent;
    }
    
    @Override
    public DataGrid process(DataGrid grid) 
    {
        if(grid.isPresenceAbsence()) return grid;
        
        ArrayList<DataLocation> locations = grid.getOrderedDataLocations();
        int qIndex = (int) Math.floor((1-paramValues[0]/100.0)*locations.size());
        double maxValue = locations.get(qIndex).value;
        
       double[][] values = MappingProject.grid.getEmptyGrid(); //filled with 0s und NoData where applicable
        
       int replaced=0;
        for(int l=0; l<locations.size();l++)
        {
            if(locations.get(l).value<=maxValue)
            {
                values[locations.get(l).x][locations.get(l).y]=locations.get(l).value;
            }
            else
            {
                values[locations.get(l).x][locations.get(l).y]=maxValue;
                replaced++;
            }
           
        }
         //System.out.println("    Replaced "+replaced+" out of "+locations.size()+ " non-zero cells with max value: " +maxValue);
        return new DataGrid(values,maxValue,grid.getMin(),grid.getNoDataValue());


    }


    @Override
    public String getName() 
    {
        return "Cut top x %";
    }

    @Override
    public PreProcessor getNewInstance() 
    {
       return new PercentCutter();
    }
    
}
