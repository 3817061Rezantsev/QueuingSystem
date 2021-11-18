package staff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Task {
	public class SuperClass {
		protected void superMethod() {
		}

		int superVar = 5;
	}

	public class SubClass extends SuperClass {
		void subMethod(SubClass obj) {
			obj.superMethod();
			int i;
			i = obj.superVar;
		}
	}

	public static void main(String[] args) {
		Task t = new Task();
		SuperClass superObj = t.new SubClass();
		SubClass subObj = t.new SubClass();
		System.out.print("sfsrf");
	}

}
