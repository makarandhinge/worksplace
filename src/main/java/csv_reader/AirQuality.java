package csv_reader;

public class AirQuality {

	String Date;
	String City;
	String CO;
	double NO2;
	double SO2;
	double O3;
	double PM2;
	double PM10;
	int AQI;
	
	
	
	public AirQuality(String date, String city, String cO, double nO2, double sO2, double o3, double pM2, double pM10,
			int aQI) {
		Date = date;
		City = city;
		CO = cO;
		NO2 = nO2;
		SO2 = sO2;
		O3 = o3;
		PM2 = pM2;
		PM10 = pM10;
		AQI = aQI;
	}
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCO() {
		return CO;
	}
	public void setCO(String cO) {
		CO = cO;
	}
	public double getNO2() {
		return NO2;
	}
	public void setNO2(double nO2) {
		NO2 = nO2;
	}
	public double getSO2() {
		return SO2;
	}
	public void setSO2(double sO2) {
		SO2 = sO2;
	}
	public double getO3() {
		return O3;
	}
	public void setO3(double o3) {
		O3 = o3;
	}
	public double getPM2() {
		return PM2;
	}
	public void setPM2(double pM2) {
		PM2 = pM2;
	}
	public double getPM10() {
		return PM10;
	}
	public void setPM10(double pM10) {
		PM10 = pM10;
	}
	public int getAQI() {
		return AQI;
	}
	public void setAQI(int aQI) {
		AQI = aQI;
	}

	@Override
	public String toString() {
		return "AirQuality [Date=" + Date + ", City=" + City + ", CO=" + CO + ", NO2=" + NO2 + ", SO2=" + SO2 + ", O3="
				+ O3 + ", PM2=" + PM2 + ", PM10=" + PM10 + ", AQI=" + AQI + "]";
	}
	
	
	
	
}
