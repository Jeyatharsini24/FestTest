import static org.fest.reflect.core.Reflection.constructor;
import static org.fest.reflect.core.Reflection.field;
import static org.fest.reflect.core.Reflection.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class NamesTest extends TestCase {
	// reflection
	@Test
	public void test_get() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Names names = new Names();
		Method method = Names.class.getDeclaredMethod("get", int.class);

		AccessController.doPrivileged(new PrivilegedAction<Void>() {
			public Void run() {
				method.setAccessible(true);
				return null;
			}
		});
		String name = (String) method.invoke(names, 1);
		assertEquals("Name2", name);
	}
	
	// FEST-reflection
	public void test_get_by_fest() {
		Names namesInstance = constructor().in(Names.class).newInstance();
		String nameFromField = (String) field("namesInClass").ofType(List.class).in(namesInstance).get().get(0);
		assertEquals("Name1", nameFromField);
		String nameFromMethod = method("get").withReturnType(String.class).withParameterTypes(int.class).in(namesInstance).invoke(1);
		assertEquals("Name2", nameFromMethod);
		
		List<String>newNamesToSet = new ArrayList<>();
		newNamesToSet.add("NewNamesToSet1");
		newNamesToSet.add("NewNamesToSet2");
		field("namesInClass").ofType(List.class).in(namesInstance).set(newNamesToSet);
		String nameFromFieldAfterSetNewValue = (String) field("namesInClass").ofType(List.class).in(namesInstance).get().get(0);
		assertEquals("NewNamesToSet1", nameFromFieldAfterSetNewValue);
	}
}