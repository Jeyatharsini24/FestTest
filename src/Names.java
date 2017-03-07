import java.util.ArrayList;
import java.util.List;

public class Names {
	private final List<String>namesInClass = new ArrayList<>();
	
	Names()
	{
		namesInClass.add("Name1");
		namesInClass.add("Name2");
	}
	
	String get(int index)
	{
		return namesInClass.get(index);
	}
}