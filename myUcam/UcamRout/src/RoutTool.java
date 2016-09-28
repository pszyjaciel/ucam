public class RoutTool {
	public double dia;
	public double speed;
	public double feed;
	public double hfeed;

	public RoutTool() {

	}

	public RoutTool(double myDia, double mySpeed, double myFeed, double myHfeed) {
		this.dia = myDia;
		this.speed = mySpeed;
		this.feed = myFeed;
		this.hfeed = myHfeed;
	}

	public RoutTool toolParam(String[] myStrArr) {
		int i = 0;
		int j = 0;

		do {
			if (!myStrArr[i].equals("")) {
				if (j == 2) {
					dia = Double.valueOf(myStrArr[i]);
				} else if (j == 5) {
					speed = Double.valueOf(myStrArr[i]); // string2double
				} else if (j == 6) {
					feed = Double.valueOf(myStrArr[i]);
				} else if (j == 10) {
					hfeed = Double.valueOf(myStrArr[i]);
				}
				j++;
			}
			i++;
		} while (i < myStrArr.length);

		RoutTool myRoutTool = new RoutTool(dia, speed, feed, hfeed);
		return myRoutTool;
	}
}
