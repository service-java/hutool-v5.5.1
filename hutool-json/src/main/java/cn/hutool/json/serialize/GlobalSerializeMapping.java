package cn.hutool.json.serialize;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.hutool.json.IJSON;

/**
 * 全局的序列化和反序列化器映射<br>
 * 在JSON和Java对象转换过程中，优先使用注册于此处的自定义转换
 *
 * @author Looly
 *
 */
public class GlobalSerializeMapping {

	private static Map<Type, IJSONSerializer<? extends IJSON, ?>> serializerMap;
	private static Map<Type, IJSONDeserializer<?>> deserializerMap;

	/**
	 * 加入自定义的序列化器
	 *
	 * @param type 对象类型
	 * @param serializer 序列化器实现
	 */
	public static void put(Type type, IJSONArraySerializer<?> serializer) {
		putInternal(type, serializer);
	}

	/**
	 * 加入自定义的序列化器
	 *
	 * @param type 对象类型
	 * @param serializer 序列化器实现
	 */
	public static void put(Type type, IJSONObjectSerializer<?> serializer) {
		putInternal(type, serializer);
	}

	/**
	 * 加入自定义的序列化器
	 *
	 * @param type 对象类型
	 * @param serializer 序列化器实现
	 */
	synchronized private static void putInternal(Type type, IJSONSerializer<? extends IJSON, ?> serializer) {
		if(null == serializerMap) {
			serializerMap = new ConcurrentHashMap<>();
		}
		serializerMap.put(type, serializer);
	}

	/**
	 * 加入自定义的反序列化器
	 *
	 * @param type 对象类型
	 * @param deserializer 反序列化器实现
	 */
	synchronized public static void put(Type type, IJSONDeserializer<?> deserializer) {
		if(null == deserializerMap) {
			deserializerMap = new ConcurrentHashMap<>();
		}
		deserializerMap.put(type, deserializer);
	}

	/**
	 * 获取自定义的序列化器，如果未定义返回{@code null}
	 * @param type 类型
	 * @return 自定义的序列化器或者{@code null}
	 */
	public static IJSONSerializer<? extends IJSON, ?> getSerializer(Type type){
		if(null == serializerMap) {
			return null;
		}
		return serializerMap.get(type);
	}

	/**
	 * 获取自定义的反序列化器，如果未定义返回{@code null}
	 * @param type 类型
	 * @return 自定义的反序列化器或者{@code null}
	 */
	public static IJSONDeserializer<?> getDeserializer(Type type){
		if(null == deserializerMap) {
			return null;
		}
		return deserializerMap.get(type);
	}
}
