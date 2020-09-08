package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AggregatorProcessor<T extends Aggregator>{

    T aggregator ;

     String file;


    AggregatorProcessor(){


    }


    public  AggregatorProcessor(T aggregator, String file){

        super();
        this.aggregator = aggregator;
        this.file = file;

    }


    public double runAggregator(int columnIndex) throws IOException {

        StockFileReader fileReader = new StockFileReader(file);
        List< String > lines = fileReader.readFileData();
        columnIndex--;
        for (String line : lines) {

            String[] numbers = line.split(",");


            System.out.println(numbers[1]);

            double value = Double.parseDouble(numbers[columnIndex]);

            aggregator.add(value);

        }

        double number = aggregator.calculate();
        return number;
    }
}