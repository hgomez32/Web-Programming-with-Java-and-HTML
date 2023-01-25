package modelhw1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Articlehw1 {
	private String title;
	private String subtitle;
	private String category; 
	private LocalDate submitted;
	private LocalDate published;
	private String content;
	private int id;
	static int idSeed = 1;

	public Articlehw1() {
		this.id = idSeed++ ;
	}
	public Articlehw1(String title, String subtitle, String category, LocalDate submitted, LocalDate published, String content) {
		this.id = idSeed++ ;
		this.title = title;
		this.subtitle = subtitle;
		this.category = category; 
		this.submitted = LocalDate.now(); 
		this.published = published;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubmitted() {
		String formattedDate = this.submitted.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		return formattedDate;
	}

	public void setSubmitted(LocalDate time) {
		this.submitted = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPublished() {
		if (this.published == null) 
			return null;
		else {
			String formattedDate = this.published.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
			return formattedDate;
		}
	}
	public void setPublished(LocalDate published) {
		this.published = published;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
