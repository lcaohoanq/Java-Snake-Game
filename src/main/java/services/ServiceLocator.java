package services;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton Service Locator Pattern implementation
 * Provides centralized access to services in the application
 */
public class ServiceLocator {
    
    private static ServiceLocator instance;
    private final Map<Class<?>, Object> services = new HashMap<>();
    
    // Private constructor to prevent direct instantiation
    private ServiceLocator() {
        // Initialize default services
        registerService(UserService.class, new UserServiceImpl());
    }
    
    /**
     * Get the singleton instance of ServiceLocator
     * 
     * @return The ServiceLocator instance
     */
    public static synchronized ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }
    
    /**
     * Register a service implementation
     * 
     * @param <T> The service interface type
     * @param serviceClass The service interface class
     * @param implementation The service implementation
     */
    public <T> void registerService(Class<T> serviceClass, T implementation) {
        services.put(serviceClass, implementation);
    }
    
    /**
     * Get a service implementation
     * 
     * @param <T> The service interface type
     * @param serviceClass The service interface class
     * @return The service implementation
     * @throws IllegalArgumentException if the service is not registered
     */
    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> serviceClass) {
        T service = (T) services.get(serviceClass);
        if (service == null) {
            throw new IllegalArgumentException("Service not registered: " + serviceClass.getName());
        }
        return service;
    }
    
    /**
     * Check if a service is registered
     * 
     * @param serviceClass The service interface class
     * @return true if the service is registered, false otherwise
     */
    public boolean isServiceRegistered(Class<?> serviceClass) {
        return services.containsKey(serviceClass);
    }
}