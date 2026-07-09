# WiFi Tariff Service
This is a test project with **an example use case**:

#### A REST API service that manages WiFi tariff packages in a boutique hotel chain, designed for Alpine Border Hotels' cross-border operations (Germany/Switzerland)..

## Functionality

(8 hour task, tech stack requirements: 
    Spring Boot 3 / Java 21+
    JSON REST interface with standard HTTP methods and status codes
    data storage of choice)

I designed a tariff JSON REST data structure that can handle the following information: name, features, prices (at least)
I developed a service with the following functionality:

    create a tariff and persist it
        input: tariff
        output: tariff (mirrored processed input)
    get a tariff
        input: tariff id
        output: tariff
    modify a tariff
        input: tariff id and tariff data
        output: updated tariff
    delete a tariff
        input: unique tariff id
        output: tariff

## Stretch tasks (optional)
Offer a swagger page to ease service testing - done - basic default integration. 
        TODO: Add more detailed documentation. (>8 hrs, so do later)


JUnit tests: cover your code to at least 80% (I didn't get to using JUnit, but I did test with api-tests.http.
        Using JUnit would be a next step if time had allowed. I will return and do this as a learning exercise in SpringBoot)

    API key authentication

    sync: a scheduled task should sync via http and copy/merge remote tariffs to the local ones

    create a docker container running the application

### Prerequisites
- Java 21 and SpringBoot 3.4.8 
(I used the Latest Stable Version of Eclipse Temurin: https://adoptium.net/temurin/releases/?version=21
  )
- No database setup required (uses embedded H2)

### Java Installation Required

  To run this service, check if you have the correct JDK first:

  java -version

  If you see version 21+, you're ready! Otherwise, install Java:
  
Option 1: Download from Oracle (Recommended)

Visit: https://www.oracle.com/java/technologies/downloads/#java21
Download Java 21 JDK for your operating system
Install using the downloaded installer
Verify: java -version should show version 21.x.x

Option 2: Using Package Managers
bash# macOS (using Homebrew)
brew install openjdk@21

### Ubuntu/Debian
sudo apt update && sudo apt install openjdk-21-jdk

### Windows (using Chocolatey)
choco install openjdk21

### Or use SDKMAN (cross-platform)
curl -s "https://get.sdkman.io" | bash
sdk install java 21.0.1-oracle

Option 3: Alternative JDK Distributions

Eclipse Temurin: https://adoptium.net/temurin/releases/?version=21
Amazon Corretto: https://aws.amazon.com/corretto/
Microsoft OpenJDK: https://docs.microsoft.com/java/

### Other Requirements

No Maven installation needed (project includes Maven wrapper)

No database setup required (uses embedded H2)

No additional tools required (all dependencies managed by Maven)

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
- **API Documentation**: http://localhost:8081/swagger-ui.html
- **H2 Database Console**: http://localhost:8081/h2-console
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
1. Once the service is running, you can run tests via the api-tests.http file (IntelliJ Ultimate has some useful features to help with this. 
2. Another option is to use Swagger. Open Swagger UI: http://localhost:8081/swagger-ui.html
2. Use "Try it out" feature for each endpoint
3. Populate sample data using provided JSON examples. Be sure to change the JSON input according to your test requirements.

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

### Repository Pattern

The repository abstraction allows controllers and services to focus on business logic rather than data access mechanics, resulting in cleaner, more maintainable code that clearly expresses business intent.
Future Extension Capabilities:
As the hotel chain grows or business requirements evolve, the repository can be extended with:

- Custom query methods using method naming conventions
- Complex business queries using @Query annotations
- Specification pattern for dynamic search functionality
- Custom repository implementations for specialized operations
- Caching annotations for performance optimization
