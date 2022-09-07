package pojoClass;

import java.util.List;

public class GetAllProductsResponse {

	private List<DataGetAllProductsResponse> data;
	private int count;
	private String message;

	public List<DataGetAllProductsResponse> getData() {
		return data;
	}

	public void setData(List<DataGetAllProductsResponse> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
