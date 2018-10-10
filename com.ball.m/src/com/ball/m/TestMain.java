package com.ball.m;

public class TestMain {
	public static void main(String[] args) {
		int[] rs = new int[33];
		for (int i = 0; i < 33; i++) {
			rs[i] = i+1;
		}

		for (int i = 0; i < 300; i++) {
			int id=(17+i/160)*100+(i%160);
			String date = (17+i/160)+"-"+(i/30)%12+"-"+i%30;
			int[] reds = getBallAry(rs, 6);
			int r1 = reds[0];
			int r2 = reds[1];
			int r3 = reds[2];
			int r4 = reds[3];
			int r5 = reds[4];
			int r6 = reds[5];
			String str = "{\"id\":\""+id+"\",\"date\":\""+date+"\",\"r1\":\""+r1+"\",\"r2\":\""+r2+"\",\"r3\":\""+r3+"\",\"r4\":\""+r4+"\",\"r5\":\""+r5+"\",\"r6\":\""+r6+"\",\"b\":\""+r1+"\"},";
			System.out.println(str);
		}

	}

	public static int getRandom(int min, int max) {
		double random = Math.random() * 100;
		try {
			Thread.sleep((long) (Math.random() * 100) / 20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int temp = (int) random;
		return (temp % (max - min + 1) + min);
	}

	public static int[] getBallAry(int[] balls, int getSize) {
		int[] temp = balls.clone();
		int[] randomBalls = new int[getSize];
		for (int i = 0; i < getSize; i++) {
			int random = getRandom(0, temp.length - 1);
			randomBalls[i] = temp[random];
			for (int j = random; j < (temp.length - 1); j++) {
				temp[j] = temp[j + 1];
			}
		}
		return randomBalls;
	}
}
