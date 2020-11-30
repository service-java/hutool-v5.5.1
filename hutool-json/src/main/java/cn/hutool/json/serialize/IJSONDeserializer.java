package cn.hutool.json.serialize;

import cn.hutool.json.IJSON;

/**
 * JSON反序列话自定义实现类
 *
 * @author Looly
 *
 * @param <T> 反序列化后的类型
 */
@FunctionalInterface
public interface IJSONDeserializer<T> {

	/**
	 * 反序列化，通过实现此方法，自定义实现JSON转换为指定类型的逻辑
	 *
	 * @param json {@link IJSON}
	 * @return 目标对象
	 */
	T deserialize(IJSON json);
}
