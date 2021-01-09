package 草稿;

public class A {
	private static String category[] = { "猫", "狗","2","3" };

	public static int getNum(int start, int end) {// 随机返回返回指定范围间的整数
		// Math.random()随机返回0.0至1.0之间的数
		return (int) (Math.random() * (end - start + 1) + start);
	}

	public static String getCategory() {
		String cate = null;
		int index = getNum(0, category.length-1);// 随机取姓氏字符串中的任意位置
		cate=category[index];
		return cate;
	}

	public static void main(String[] args) {
		System.out.println(category.length);
			System.out.println(getCategory());
	}
}