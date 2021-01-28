package pojo;

import java.sql.Date;

public class IssueRecord {
		private int issueId;
		private int copyID;
		private int userID;
		private Date issueDate;
		private Date returnDuedate;
		private Date returnDate;
		private double fine;

		public IssueRecord() { }
		public int getIssueId() {
			return issueId;
		}
		public void setIssueId(int issueId) {
			this.issueId = issueId;
		}
		public int getCopyID() {
			return copyID;
		}
		public void setCopyID(int copyID) {
			this.copyID = copyID;
		}
		public int getUserID() {
			return userID;
		}
		public void setUserID(int userID) {
			this.userID = userID;
		}
		public Date getIssueDate() {
			return issueDate;
		}
		public void setIssueDate(Date issueDate) {
			this.issueDate = issueDate;
		}
		public Date getReturnDuedate() {
			return returnDuedate;
		}
		public void setReturnDuedate(Date returnDuedate) {
			this.returnDuedate = returnDuedate;
		}
		public Date getReturnDate() {
			return returnDate;
		}
		public IssueRecord(int issueId, int copyID, int userID, Date issueDate, Date returnDuedate, Date returnDate,
				double fine) {
		
			this.issueId = issueId;
			this.copyID = copyID;
			this.userID = userID;
			this.issueDate = issueDate;
			this.returnDuedate = returnDuedate;
			this.returnDate = returnDate;
			this.fine = fine;
		}
		@Override
		public String toString() {
			return "IssueRecords   [     issueId=" + issueId + ",       copyID=" + copyID +  ",     returnDate=" + returnDate +  "]";
		}
		
		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}
		public double getFine() {
			return fine;
		}
		public void setFine(double fine) {
			this.fine = fine;
		}

}
