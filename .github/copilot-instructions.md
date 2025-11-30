# Retasify - AI Coding Instructions

## Project Overview
Retasify (package: `com.nepobabies.retas`) is a **sustainable fashion Android app** built with Kotlin. It helps users reduce their fashion footprint through donation tracking, upcycling guides, and gamification (virtual bear customization).

## Architecture & Package Structure

### Package Organization
```
com.nepobabies.retas/
├── ui/                    # All UI components
│   ├── splash/           # SplashActivity
│   ├── auth/             # AuthActivity, LoginActivity, SignUpActivity
│   ├── main/             # MainActivity (bottom nav host)
│   ├── dashboard/        # DashboardFragment
│   ├── donate/           # DonateFragment, DonateDetailsActivity
│   ├── upcycle/          # UpcycleFragment, PartnerDetailFragment
│   ├── bear/             # BearFragment, CustomizeBearFragment, ClothesPageFragment
│   ├── profile/          # ProfileFragment
│   └── calculator/       # FashionCalculatorActivity, ResultActivity
├── data/
│   ├── model/            # Data classes (User, Donation, Partner, BearClothing)
│   └── repository/       # Repository pattern (UserRepository, DonationRepository, PartnerRepository)
└── utils/                # Utilities (FootprintCalculator, PreferencesManager)
```

### App Flow
```
SplashActivity → AuthActivity → LoginActivity/SignUpActivity → MainActivity (BottomNavigationView)
```

### Main Tabs (5-tab bottom navigation)
| Tab | Fragment | Purpose |
|-----|----------|---------|
| Upcycle | `UpcycleFragment` | Partner upcycling services |
| Donate | `DonateFragment` | Clothing donation flow (emoji condition selector → `DonateDetailsActivity`) |
| Dashboard | `DashboardFragment` | Main hub (default tab) |
| Bear | `BearFragment` | Virtual bear dress-up gamification |
| Profile | `ProfileFragment` | User profile & settings |

## Navigation Patterns

### Bottom Nav Tabs
Use `FragmentManager.replace()` in `MainActivity`:
```kotlin
private fun replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.frame_layout, fragment)
        .commit()
}
```

### In-Feature Flows
Use Jetpack Navigation Component with `nav_graph.xml`:
```kotlin
findNavController().navigate(R.id.action_source_to_destination)
findNavController().navigateUp()
```

## Tech Stack & Build

### Key Dependencies
- **UI**: View Binding (enabled), Material Components, CircleImageView
- **Navigation**: AndroidX Navigation 2.7.7 (Fragment + UI KTX)
- **Compose**: Available but secondary - primary UI is XML layouts
- **Target SDK**: 36, Min SDK: 23, JVM: 11

### Build Commands
```powershell
.\gradlew assembleDebug    # Debug APK
.\gradlew installDebug     # Install to device
.\gradlew build            # Full build
```

## Code Patterns

### Fragment with ViewBinding
```kotlin
class ExampleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_example, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup UI here
    }
}
```

### Activity Transitions with Intent Extras
```kotlin
val intent = Intent(this, TargetActivity::class.java).apply {
    putExtra("key", value)
}
startActivity(intent)
```

### Business Logic in Object Singletons
```kotlin
object FootprintCalculator {
    fun calculateScore(...): Int { ... }
    fun classifyFootprint(score: Int): String { ... }
}
```

### Repository Pattern (Placeholder for Firebase)
```kotlin
class DonationRepository {
    suspend fun createDonation(donation: Donation): Result<Donation> { ... }
    suspend fun getDonationsByUser(userId: String): List<Donation> { ... }
}
```

## Resource Conventions

### Colors (`res/values/colors.xml`)
- Primary: `@color/green` (#48583B)
- Accent: `@color/yellow` (#F4B840)
- Background: `@color/beige` (#FAF5EF)

### Typography
- Font: Montserrat (`@font/montserrat_regular`, `@font/montserrat_bold`)

### Layout Naming
- Activities: `activity_*.xml`
- Fragments: `fragment_*.xml`
- List items: `item_*.xml`

## Key Files Reference
| Purpose | Location |
|---------|----------|
| Bottom nav menu | `res/menu/bottom_nav.xml` |
| Navigation graph | `res/navigation/nav_graph.xml` |
| Theme/styles | `res/values/themes.xml` |
| Version catalog | `gradle/libs.versions.toml` |
| Data models | `data/model/` |
| Repositories | `data/repository/` |

## TODO: Firebase Integration
All repository classes and auth activities have placeholder implementations marked with `// TODO:` comments for future Firebase integration:
- Firebase Auth for login/signup
- Firestore for User, Donation, Partner data
- Firebase Storage for images
