
# Online Berber

Bu projede, online berber randevu sisteminin backend servisini geliştirdim. Amacım, kullanıcıların çevrim içi olarak kolayca randevu alabilmelerini sağlamak oldu. Servisi, SOLID prensiplerine uygun olarak ve Spring Boot, MySQL, Spring Security kullanarak yapılandırdım.Proje şu anlık monolitik yapıda amacım projeyi mikroservis mimarisine dönüştürmek ve frontend tarafını geliştirip yayına almak. 


## Mimari

![App Screenshot](https://furkancan.dev/assets/assets/onlinebarber.png)




## AppointmentController

### Appointment Endpoints
- **POST** /api/v1/appointment/create/{customerId}/{modelId}
- **DELETE** /api/v1/appointment/delete/{id}
- **GET** /api/v1/appointment/list/{shopId}
- **GET** /api/v1/appointment/cList/{customerId}

## AuthController

### Authentication Endpoints
- **POST** /api/v1/auth/login
- **POST** /api/v1/auth/logout

## CustomerController

### Customer Endpoints
- **POST** /api/v1/customer/save
- **POST** /api/v1/customer/sendActivationCode
- **PATCH** /api/v1/customer/active

## ShavingModelController

### Shaving Model Endpoints
- **POST** /api/v1/smodel/add/{id}
- **DELETE** /api/v1/smodel/delete/{id}
- **PUT** /api/v1/smodel/update/{id}
- **GET** /api/v1/smodel/list/{shopId}
- **GET** /api/v1/smodel/{id}

## ShopController

### Shop Endpoints
- **POST** /api/v1/shop/open
- **PUT** /api/v1/shop/update/{id}
- **DELETE** /api/v1/shop/delete/{id}
- **GET** /api/v1/shop/list
- **GET** /api/v1/shop/list/{value}


