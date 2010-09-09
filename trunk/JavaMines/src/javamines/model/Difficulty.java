package javamines.model;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    public static Difficulty fromString(String name) {
        return getEnumFromString(Difficulty.class, name);
    }
    
    public String toString() {
    	String ret = "";
    	
    	switch(this) {
	    	case EASY:
	    		ret = "easy";
	    		break;
	    	case MEDIUM:
	    		ret = "medium";
	    		break;
	    	case HARD:
	    		ret = "hard";
	    		break;
	    	default:
	    		break;
    	}
    	
    	return ret;
    }
    
    /**
     * little helper method to retrieve a enum reference from a given String
     * 
     * @param <T>
     * @param c
     * @param string
     * @return
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            }
            catch(IllegalArgumentException ex){}
        }
        
        return null;
    }

}
