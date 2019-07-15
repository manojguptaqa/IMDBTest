package ObjectClasses;

import java.util.Comparator;

public class bkp {
	private String id;
	private String poster_src;
	private String movieName;
	private String year;
	private String countOfRatings;
	private String rating;
	
	
	public bkp(String rank,String poster_src,String movie_Title,String movie_year,String movie_Total_Votes,String movie_Rating)
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



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCountOfRatings() {
		return countOfRatings;
	}
	public void setCountOfRatings(String countOfRatings) {
		this.countOfRatings = countOfRatings;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}

//	@Override
//	public int compareTo(Movie m){
//	    return Comparator.comparingInt(Movie::getid)
//	              .thenComparing(Movie::getMovieName)
//	              .thenComparingInt(Movie::getPoster_src)
//	              .thenComparingInt(Movie::getCountOfRatings)
//	              .thenComparingInt(Movie::getRating)
//	              .thenComparingInt(Movie::getYear)
//	              .compare(this, m);
//	}

	
	//@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        bkp movie = (bkp) o;
        return id.equals(movie.id) &&
        		poster_src.equals(movie.poster_src) &&
        		movieName.equals(movie.movieName)&&
        		year.equals(movie.year)&&
        		countOfRatings.equals(movie.countOfRatings)&&
        		rating.equals(movie.rating);
        		
    }
	

	
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(id+":{id="+id+" poster_src="+poster_src+" movieName="+movieName+" year="+year+" countOfRatings="+countOfRatings+" rating="+rating+"}");
		
	}

}

