package ObjectClasses;

import java.util.Comparator;

public class Movie{
	private int id;
	private String poster_src;
	private String movieName;
	private int year;
	private int countOfRatings;
	private float rating;
	
	
	public Movie(int rank,String poster_src,String movie_Title,int movie_year,int movie_Total_Votes,float movie_Rating)
	{
		this.id=rank;
		this.poster_src=poster_src;
		this.movieName=movie_Title;
		this.year=movie_year;
		this.countOfRatings=movie_Total_Votes;
		this.rating=movie_Rating;
	}
	
	
	
	public String getPoster_src() {
		return poster_src;
	}



	public void setPoster_src(String poster_src) {
		this.poster_src = poster_src;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCountOfRatings() {
		return countOfRatings;
	}
	public void setCountOfRatings(int countOfRatings) {
		this.countOfRatings = countOfRatings;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

//	 public int compareTo(Object obj) {
//		    Movie emp = (Movie) obj;
//		    int deptComp = id.compareTo(emp.getId());
//
//		    return ((deptComp == 0) ? lastName.compareTo(emp.getLastName()) : deptComp);
//		  }

	
	//@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        return id==(movie.id) &&
        		poster_src.equals(movie.poster_src) &&
        		movieName.equals(movie.movieName)&&
        		year==(movie.year)&&
        		countOfRatings==(movie.countOfRatings)&&
        		rating==(movie.rating);
        		
    }
	

	
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(id+":{id="+id+" poster_src="+poster_src+" movieName="+movieName+" year="+year+" countOfRatings="+countOfRatings+" rating="+rating+"}");
		
	}





}

