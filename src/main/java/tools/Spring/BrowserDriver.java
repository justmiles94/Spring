package tools.Spring;

public class BrowserDriver {
	public void openWebConnection() {
		WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
		try {
			HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
			Map<String, String> headers = page.getWebResponse().getWebRequest().getAdditionalHeaders();
			for (String key : headers.keySet()) {
				System.out.print(key + ", ");
			}
			System.out.println();
			for (String key : headers.values()) {
				System.out.print(key + ", ");
			}
			System.out.println();
			System.out.println(page.getTitleText());
			webClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
