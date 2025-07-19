# /features

Organized by domain/feature (feature-based structure).

## Convention:
Each domain is placed in its own subfolder (e.g., `auth`, `dashboard`, `settings`) and manages its own logic.

### Structure inside each domain:
- `components/`: UI components specific to the domain.
- `hooks/`: Domain-related hooks (usually wrapping useQuery/useMutation).
- `pages/`: Page components used in routing.
- `services/` or `api/`: Handles domain API calls, separating mutation/query logic.
- `types.ts`: Interfaces specific to the domain.

> Do not use code directly between features. Use `shared/` for reusable logic.
