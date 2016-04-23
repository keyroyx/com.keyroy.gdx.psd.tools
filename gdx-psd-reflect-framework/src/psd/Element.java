package psd;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.keyroy.util.json.JsonAn;

/**
 * PSD 元素
 * 
 * @author roy
 */
public class Element {
	// 图层名称
	public String layerName;
	// 坐标 , 大小
	public int x, y, width, height;
	// 绑定参数
	public List<Param> params;
	// 是否显示
	public boolean isVisible;
	// 父类对象
	@JsonAn(skip = true)
	protected Folder parent;
	// 自定义对象
	@JsonAn(skip = true)
	protected Object userObject;
	// 自定义对象
	@JsonAn(skip = true)
	protected Actor actor;

	// 设置用户自定义的缓存数据
	public final void setUserObject(Object userObject) {
		this.userObject = userObject;
	}

	//
	@SuppressWarnings("unchecked")
	public final <T> T getUserObject() {
		return (T) userObject;
	}

	// 设置用户自定义的 Actor 对象
	public final void setActor(Actor actor) {
		this.actor = actor;
	}

	//
	@SuppressWarnings("unchecked")
	public final <T extends Actor> T getActor() {
		return (T) actor;
	}

	// 设置 文件夹
	public final void setParent(Folder parent) {
		this.parent = parent;
	}

	// 获取 父文件夹
	public final Folder getParent() {
		return parent;
	}

	// 获取当前的路径
	public final String getPath() {
		Array<String> paths = new Array<String>();
		paths.add(layerName);
		Folder folder = parent;
		while (folder != null) {
			if (folder.layerName != null) {
				paths.add(folder.layerName);
			} else if (folder instanceof PsdFile) {
				paths.add(((PsdFile) folder).psdName);
			}
			folder = folder.parent;
		}

		StringBuffer buffer = new StringBuffer();
		for (int i = paths.size - 1; i >= 0; i--) {
			String name = paths.get(i);
			buffer.append(name);
			if (name.equals(layerName) == false) {
				buffer.append("/");
			}
		}
		return buffer.toString();
	}

	@Override
	public String toString() {
		return getPath();
	}

}
