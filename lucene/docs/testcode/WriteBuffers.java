import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WriteBuffers implements Closeable {

    Map<Long, BufferedWriter> writeBuffers = new HashMap<Long, BufferedWriter>();
    private long beginTimeStamp = 0;
    private long endTimeStamp = 0;
    private long interval = 0;

    public WriteBuffers(long beginTimeStamp, long endTimeStamp, long interval) throws IOException {
        this.beginTimeStamp = beginTimeStamp;
        this.endTimeStamp = endTimeStamp;
        this.interval = interval;
        for(long curTime=beginTimeStamp; curTime<endTimeStamp; curTime+=interval){
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\luceneDemo\\"+curTime+".txt"));
            writeBuffers.put(curTime, bw);
        }
    }


    @Override
    public void close() throws IOException {
        for (BufferedWriter buffer : writeBuffers.values()) {
            buffer.flush();
            buffer.close();
        }
    }

    public boolean put(long timeStamp, String value) throws IOException {
        long timeArea = timeStamp / interval * interval;
        if(writeBuffers.containsKey(timeArea)){
            writeBuffers.get(timeArea).write(value);
            writeBuffers.get(timeArea).newLine();
            return true;
        }
        return false;

    }

    public static void main(String[] args) throws ParseException, IOException {
        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String beginTimeStr = "2018-11-01 00:00:00";
        String endTimeStr = "2018-12-01 00:00:00";
        long beginTime = sf.parse(beginTimeStr).getTime();
        long endTime = sf.parse(beginTimeStr).getTime();
        try(WriteBuffers buffers = new WriteBuffers(beginTime, endTime, 60*1000)){
            for (long time = beginTime; time<endTime; ++time)
            {
                if(buffers.put(time, sf.format(sf.format(time)) + ":"+UUID.randomUUID())){
                    System.out.println("写入失败");
                }
            }
        }
    }

}
