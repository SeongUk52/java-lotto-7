package lotto.view;

import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    public void printLottoLogs(List<String> lottoLogs) {
        System.out.println("\n" + lottoLogs.size() + "개를 구매했습니다.");
        lottoLogs.forEach(System.out::println);
    }

    public void printWinningReport(List<String> winningReport) {
        System.out.println("당첨 통계\n" + "---");
        winningReport.forEach(System.out::println);
    }

    public void printProfitRate(double profitRate) {
        DecimalFormat profitRateFormat = new DecimalFormat("#,##0.0#%");
        System.out.println("총 수익률은 " + profitRateFormat.format(profitRate) + "입니다.");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
