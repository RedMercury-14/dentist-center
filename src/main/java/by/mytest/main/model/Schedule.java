package by.mytest.main.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 7438208061362199236L;
	
	public Schedule() {}

	public Schedule(LocalDate date, LocalTime time, String commentSchedule, String status, User user) {
		super();
		this.localDate = date;
		this.localTime = time;
		this.commentSchedule = commentSchedule;
		this.status = status;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idschedule")
	private int idSchedule;
	
	@Column(name="date")
	private LocalDate localDate;
	
	@Column(name="time")
	private LocalTime localTime;
	
	@Column(name="comment_schedule")
	private String commentSchedule;
	
	@Column(name="status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_Login")
	private User user;
	
	@Transient
	private String login;
	
	@Transient
	private String date;
	
	
	
	public String getDate() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dateFormatterNew = DateTimeFormatter.ofPattern("d MMMM yyyy, EEE");
		LocalDate ldateTime = LocalDate.parse(localDate.toString(), dateFormatter);
		return dateFormatterNew.format(ldateTime);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLogin() {
		return user.getLogin();
	}

	public void setLogin(String login) {
		user = new User();
		user.setLogin(login);
		this.login = login;
	}

	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="schedule",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	private List<Order> order;

	public int getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(int idSchedule) {
		this.idSchedule = idSchedule;
	}

	public LocalDate getLocalDate() {		
		return localDate;
	}

	public void setLocalDate(Date date) {
		this.localDate = date.toLocalDate();
	}
	
	public void addOnlyLocalDate(LocalDate date) {
		this.localDate = date;
	}

	public LocalTime getLocalTime() {
		return localTime;
	}

	public void setLocalTime(Time time) {
		this.localTime = time.toLocalTime();
	}

	public String getCommentSchedule() {
		return commentSchedule;
	}

	public void setCommentSchedule(String commentSchedule) {
		this.commentSchedule = commentSchedule;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(localDate, localTime, login);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return Objects.equals(localDate, other.localDate) && Objects.equals(localTime, other.localTime)
				&& Objects.equals(login, other.login);
	}

	@Override
	public String toString() {
		return "Schedule [localDate=" + localDate + ", localTime=" + localTime + ", commentSchedule=" + commentSchedule
				+ ", status=" + status + ", user=" + user + "]";
	}
	

	

}
