package src;

public class TicketInquiry {

    public static TicketOrderService getService() {
        return new TicketOrderService();
    }

}