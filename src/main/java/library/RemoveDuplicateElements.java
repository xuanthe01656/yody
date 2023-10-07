package library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicateElements {
	public List<Integer> remove_duplicate_elements_int_list(List<Integer> list){ 
		 Set<Integer> set = new HashSet<Integer>(list);
        // Chuyển LinkedHashSet lại trở về mảng 
		 List<Integer> listWithoutDuplicateElements = new ArrayList<>(set);
		 return listWithoutDuplicateElements;
    }
	public static List<String> remove_duplicate_elements_string_list(List<String> list){ 
		 Set<String> set = new HashSet<String>(list);
	        // Chuyển LinkedHashSet lại trở về mảng 
			 List<String> listWithoutDuplicateElements = new ArrayList<>(set);
			 return listWithoutDuplicateElements;
    }
	
	public Integer[] remove_duplicate_elements_integer(Integer arr[], int n){  
        // Chuyển mảng đã cho thành LinkedHashSet và xoá các phần tử trùng nhau
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(Arrays.asList(arr));
        
        // Chuyển LinkedHashSet lại trở về mảng 
        arr = hashSet.toArray(new Integer[0]);
       return arr;
    }
	public String[] remove_duplicate_elements_string(String arr[], int n){  
        // Chuyển mảng đã cho thành LinkedHashSet và xoá các phần tử trùng nhau
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(Arrays.asList(arr));
        
        // Chuyển LinkedHashSet lại trở về mảng 
        arr = hashSet.toArray(new String[0]);
       return arr;
    }
}
