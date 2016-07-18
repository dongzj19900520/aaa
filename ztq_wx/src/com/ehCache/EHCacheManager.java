package com.ehCache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheManager
{
	
	private static class EHCacheManagerHolder {
		private static final EHCacheManager  ehCacheManager = new EHCacheManager();
	}
  private static CacheManager cacheManager = null;

  private EHCacheManager()
  {
    try
    {
      if (cacheManager != null) return;
      cacheManager = CacheManager.getInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static EHCacheManager getInstance()
  {
    return  EHCacheManagerHolder.ehCacheManager;
  }

  public void put(String cacheName, Serializable key, Serializable value)
  {
    Cache cache = cacheManager.getCache(cacheName);
    Element element = new Element(key, value);
    cache.put(element);
  }

  public void put(String cacheName, Object key, Object value)
  {
    Cache cache = cacheManager.getCache(cacheName);
    Element element = new Element(key, value);
    cache.put(element);
  }

  public boolean remove(String cacheName, Serializable key)
  {
    Cache cache = cacheManager.getCache(cacheName);
    return cache.remove(key);
  }

  public boolean remove(String cacheName, Object key)
  {
    Cache cache = cacheManager.getCache(cacheName);
    return cache.remove(key);
  }

  public Serializable get(String cacheName, Serializable key)
    throws CacheException
  {
    Cache cache = cacheManager.getCache(cacheName);
    Element element = cache.get(key);
    if (element == null)
      return null;
    return element.getValue();
  }

  public Serializable get(String cacheName, Object key)
    throws CacheException
  {
    Cache cache = cacheManager.getCache(cacheName);
    Element element = cache.get(key);
    if (element == null)
      return null;
    return element.getValue();
  }

  public int getSize(String cacheName)
    throws CacheException
  {
    Cache cache = cacheManager.getCache(cacheName);
    return cache.getSize();
  }





  public void shutdown() {
    cacheManager.shutdown();
  }

  public CacheManager getManager() {
    return cacheManager;
  }

  public static void main(String[] args)
    throws Exception
  {
  }
}