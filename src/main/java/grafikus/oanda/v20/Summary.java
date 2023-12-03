package grafikus.oanda.v20;

import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
public class Summary {
    public static void main(String[] args) {
        com.oanda.v20.Context ctx = new com.oanda.v20.Context("https://api-fxpractice.oanda.com","4b1d82ae730663b7ef67158cf1ffc363-efe9da7155f6afdb448d3e89dc81f9a9");
        try {
            AccountSummary summary = ctx.account.summary(new AccountID("101-004-27353979-001")).getAccount();
            System.out.println(summary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

