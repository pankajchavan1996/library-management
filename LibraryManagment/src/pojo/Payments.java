package pojo;

import java.util.Date;

public class Payments {
		private int paymentID;
		private int userId;
		private double amount;
		private String type;
		private Date transanction_time;
		private Date nextPaymentDueDate;
		public Payments(int userId, double amount, String type, Date transanction_time, Date nextPaymentDueDate) {
			super();
			this.userId = userId;
			this.amount = amount;
			this.type = type;
			this.transanction_time = transanction_time;
			this.nextPaymentDueDate = nextPaymentDueDate;
		}
		
		public Payments() {	}

		public int getPaymentID() {
			return paymentID;
		}
		public void setPaymentID(int paymentID) {
			this.paymentID = paymentID;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Date getTransanction_time() {
			return transanction_time;
		}
		public void setTransanction_time(Date transanction_time) {
			this.transanction_time = transanction_time;
		}
		public Date getNextPaymentDueDate() {
			return nextPaymentDueDate;
		}
		public void setNextPaymentDueDate(Date nextPaymentDueDate) {
			this.nextPaymentDueDate = nextPaymentDueDate;
		}
		
		
}
