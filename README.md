# WiFi Tariff Service
This is a test project with an example use case:

A REST API service for managing WiFi tariff packages in a boutique hotel chain. Built with modern Java 21 and Spring Boot 3.x, designed for Alpine Border Hotels' cross-border operations (Germany/Switzerland).

## Quick Start

### Prerequisites
- Java 21 and SpringBoot 3.4.8
- No database setup required (uses embedded H2)

### Run the Application
```bash
# Clone the repository
git clone [your-repository-url]
cd wifi-tariff-service

# Run with Maven wrapper (no Maven installation needed)
./mvnw spring-boot:run        # Mac/Linux
mvnw.exe spring-boot:run      # Windows

# Alternative: Run with IDE
# Import as Maven project and run WifiTariffServiceApplication.java
```

### Verify It's Working
- **API Documentation**: http://localhost:8080/swagger-ui.html
- **H2 Database Console**: http://localhost:8080/h2-console
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: (leave blank)

## 📋 API Overview

### Base URL
```
http://localhost:8080/api/tariffs
```

### Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/tariffs` | List all tariffs |
| `GET` | `/api/tariffs/{id}` | Get tariff by ID |
| `POST` | `/api/tariffs` | Create new tariff |
| `PUT` | `/api/tariffs/{id}` | Update existing tariff |
| `DELETE` | `/api/tariffs/{id}` | Delete tariff |

### Sample Request
```json
POST /api/tariffs
{
  "name": "Business Day WiFi",
  "downloadMbps": 100,
  "uploadMbps": 50,
  "durationHours": 24,
  "maxDevices": 5,
  "basePrice": 19.99,
  "currency": "EUR",
  "billingType": "ONE_TIME"
}
```

## 🧪 Testing

### Interactive Testing
Use the included `api-tests.http` file in IntelliJ IDEA or VS Code (REST Client extension):
- Complete tariff catalog population
- CRUD operation demonstrations
- Error handling validation
- Business scenario testing

### Manual Testing
1. Open Swagger UI: http://localhost:8080/swagger-ui.html
2. Use "Try it out" feature for each endpoint
3. Populate sample data using provided JSON examples

## Business Context

### Example Use Case: Alpine Border Hotels
- **5 luxury properties** on German-Swiss border
- **Multi-currency support**: EUR (Germany) / CHF (Switzerland)
- **Customer segments**: Loyalty members, business travelers, conference attendees
- **Service model**: Complete WiFi packages (not configurable components)

### Tariff Categories
- **Loyalty Program** (Free): Blue → Silver → Gold → Platinum tiers
- **Pay-per-Use** (€4.99 - €29.99): Quick access to weekend packages
- **Conference & Events** (€99.99 - €199.99): High-bandwidth, multi-device
- **Swiss Properties** (CHF): Equivalent services with local pricing

## Architecture Highlights

### Technology Stack
- **Java 21** - Latest LTS 
- **Spring Boot 3.x** - Industry-standard framework
- **H2 Database** - Embedded for demo; easily migrates to PostgreSQL
- **Maven** - Build and dependency management
- **Swagger/OpenAPI** - Comprehensive API documentation

### Design Decisions
- **Flat data structure** - Optimized for 1:1 relationships and business model. Denormalised for small use case.
- **Repository pattern** - Enables easy database migration (H2 → PostgreSQL)
- **BigDecimal for pricing** - Financial precision for exact monetary calculations
- **Comprehensive validation** - See test-ap.http file. Includes validation scenarios. Operation-specific rules (create vs update)
- **Global error handling** - Standardized error responses with field-level details

### Data Model
```java
@Entity
public class Tariff {
    private Integer id;              // Auto-generated
    private String name;             // "Premium Business WiFi"
    private Integer downloadMbps;    // 150
    private Integer uploadMbps;      // 75
    private Integer durationHours;   // 24
    private Integer maxDevices;      // 5
    private BigDecimal basePrice;    // 19.99
    private Currency currency;       // EUR, CHF, USD, GBP, CAD
    private BillingType billingType; // ONE_TIME, HOURLY, DAILY, etc.
    // ... timestamps and validation
}
```

## Key Features

### Business Logic
- **Multi-currency support** for cross-border operations
- **Example tariff catalog** based on hotel industry analysis (Section in the api-test.http file)
- **Loyalty program tariffs ** with progressive value tiers

### Technical 
- **Comprehensive error handling** with meaningful messages
- **Validation groups** for operation-specific rules (Create/Update)
- **Logging** and monitoring ready

### Quality Assurance
- **End-to-end testing** with complete HTTP test suite
- **Edge case handling** for invalid data, missing resources
- **Business scenario validation** with realistic use cases
- **Iterative development** build from thinking about a realistic use case

## Sample Data

The service includes a realistic catalog of tariffs:

### Loyalty Program (Free) Example
```json
{
  "name": "Gold Member Business WiFi",
  "downloadMbps": 100,
  "uploadMbps": 50,
  "durationHours": 24,
  "maxDevices": 5,
  "basePrice": 0.00,
  "currency": "EUR",
  "billingType": "ONE_TIME"
}
```

### Pay-per-Use Example
```json
{
  "name": "Full-Day Premium WiFi",
  "downloadMbps": 150,
  "uploadMbps": 75,
  "durationHours": 24,
  "maxDevices": 5,
  "basePrice": 19.99,
  "currency": "EUR",
  "billingType": "ONE_TIME"
}
```

## Production Considerations

### Database Migration Path
```yaml
# Current: H2 (Development/Demo)
spring:
  datasource:
    url: jdbc:h2:mem:testdb

# Future: PostgreSQL (Production)
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tariffs
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

## 📁 Project Structure

```
src/
├── main/java/com/wifi/tariff/
│   ├── WifiTariffServiceApplication.java
│   ├── TariffController
│   ├── exception/
│       ├── ErrorResponse.java
│       └── GlobalExceptionHandler.java
│   ├── model/
│       ├── BillingType.java
│       ├── Currency.java
│       └── Tariff.java
│   ├── repository/
│   │   └── TariffRepository.java
│   ├── validation/
│       ├── ValidationGroups.java
├── main/resources/
│   ├── application.properties
│  
└── test/
    └── [test files]
```

## Development Notes

### Why Flat Structure?
After analyzing the business domain:
- Each tariff has exactly **one** set of features and **one** pricing model
- Hotel WiFi packages are complete products, not configurable components, and are quite limited in number
- 1:1 relationships didn't benefit from normalization
- Simpler queries and better performance for this use case

### Financial Data Handling
Uses `BigDecimal` for all monetary values to ensure:
- Exact precision for currency calculations
- Compliance with financial industry standards
- Proper handling of EUR/CHF exchange scenarios
- Audit-trail accuracy for accounting systems (future proof)
