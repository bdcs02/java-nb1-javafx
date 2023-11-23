package grafikus.oanda.v20;

import com.oanda.v20.account.AccountID;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.transaction.TransactionID;
import com.oanda.v20.transaction.TransactionID;
import com.oanda.v20.trade.TradeCloseRequest;
import com.oanda.v20.trade.TradeSpecifier;

import java.util.List;

public abstract class StepByStepOrder {
    static com.oanda.v20.Context ctx;
    public static AccountID accountId;
    public static void main(String[] args) {
        ctx = new com.oanda.v20.ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("StepByStepOrder").build();
        accountId = Config.ACCOUNTID;
        if(true) Nyitás();
        System.out.println("Done");
    }
    static void Nyitás(){
        System.out.println("Place a Market Order");
        InstrumentName instrument = new InstrumentName("NZD_USD");
        try {
            OrderCreateRequest request = new OrderCreateRequest(accountId);
            MarketOrderRequest marketorderrequest = new MarketOrderRequest();
            marketorderrequest.setInstrument(instrument);
// Ha pozitív, akkor LONG, ha negatív, akkor SHORT:
            marketorderrequest.setUnits(-10);
            request.setOrder(marketorderrequest);
            OrderCreateResponse response = ctx.order.create(request);
            System.out.println("tradeId: "+response.getOrderFillTransaction().getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}





