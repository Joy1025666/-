package �ݸ�;

public class A {
	private static String category[] = { "è", "��","2","3" };

	public static int getNum(int start, int end) {// ������ط���ָ����Χ�������
		// Math.random()�������0.0��1.0֮�����
		return (int) (Math.random() * (end - start + 1) + start);
	}

	public static String getCategory() {
		String cate = null;
		int index = getNum(0, category.length-1);// ���ȡ�����ַ����е�����λ��
		cate=category[index];
		return cate;
	}

	public static void main(String[] args) {
		System.out.println(category.length);
			System.out.println(getCategory());
	}
}