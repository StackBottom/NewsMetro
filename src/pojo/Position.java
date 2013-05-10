package pojo;

import java.util.List;

public class Position {
	private int id;
	private boolean isRss;
	private String url;
	private String path;
	private String context;
	private String name;
	private String lastHref;
	private List<String> tags;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isRss() {
		return isRss;
	}
	public void setRss(boolean isRss) {
		this.isRss = isRss;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastHref() {
		return lastHref;
	}
	public void setLastHref(String lastHref) {
		this.lastHref = lastHref;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
}
