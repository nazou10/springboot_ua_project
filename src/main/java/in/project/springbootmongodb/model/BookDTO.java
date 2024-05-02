package in.project.springbootmongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Books") 

public class BookDTO {
	@Id
	private String id;
	
	@NotNull(message = "Book cannot be null")	
	private String Book;
	
	@NotNull(message = "description cannot be null")
	private String description;
		
	@NotNull(message = "availibilityy cannot be null")
	private Boolean availibility;
	
	
	
	private Date createdAt;
	
	

	public void setCreatedAt(Date createdAt) {
		this.createdAt=new Date(System.currentTimeMillis());
	}

	private Date updatedAt;
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public String getBook() {
		return Book;
	}
 
	public void setBook(String Book) {
		this.Book = Book;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
 
	public Boolean getavailibility() {
		return availibility;
	}
 
	public void setavailibility(Boolean availibilityy) {
		this.availibility = availibilityy;
	}
 
	public Date getCreatedAt() {
		return createdAt;
	}
 
	
 
	public Date getUpdatedAt() {
		return updatedAt;
	}
 
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
 

}
