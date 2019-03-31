import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("GCログの場所を入力してください。 ");
            String logfile = new Scanner(System.in).nextLine();
            BufferedReader stream = new BufferedReader(new InputStreamReader(new FileInputStream(new File(logfile))));

            String line = "";
            boolean isG1GC = false;
            int processed_lines = 0;
            int stop_times = 0;
            BigDecimal total_stop_time = new BigDecimal("0.0");
            BigDecimal totaltime = new BigDecimal("0.0");
            BigDecimal biggest = new BigDecimal("0.0");

            //for skip header(jvminfo etc)
            for (int i = 0; i < 3; i++) stream.readLine();

            while ((line = stream.readLine()) != null) {
                boolean isStopTime = true;
                if (!isG1GC && line.contains("GC pause")) isG1GC = true;
                if (isG1GC && !line.contains("GC pause")) isStopTime = false;
                if (!line.contains("secs")) continue; //[GC concurrent-cleanup-start] etc

                String sec = line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf((" secs"))).trim();
                System.out.println("Processed " + sec);
                BigDecimal secb = new BigDecimal(sec);

                totaltime = totaltime.add(secb);
                if (isStopTime) {
                    total_stop_time = total_stop_time.add(secb);
                    if ((biggest.compareTo(secb)) == -1) biggest = secb;
                }

                ++processed_lines;
                if (isStopTome) ++stop_times;
            }

            stream.close();
            System.out.println();
            System.out.println("###############");
            System.out.println("ログファイル名   = " + logfile);
            System.out.println("合計GC処理時間  = " + totaltime.toString() + " sec");
            System.out.println("合計停止時間    = " + total_stop_time.toString() + " sec");
            System.out.println("最長停止時間    = " + biggest.toString() + " sec");
            System.out.println("平均停止時間    = " + total_stop_time.divide(new BigDecimal(processed_lines), RoundingMode.HALF_UP) + "sec");
you
            System.out.println("GC停止回数     = " + stop_times);

            System.out.println("###############");
            System.out.println();
            System.out.println("CTRL+Cで停止します。");
        }
    }
}
