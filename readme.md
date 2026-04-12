# Implementación de GitFlow

## Metodología de branching elegida: GitFlow

Se eligió **GitFlow** como estrategia de control de versiones para este proyecto.

### Justificación

GitFlow fue elegido porque:

- Separa el código estable (`main`) del código en integración (`develop`), reduciendo riesgos en producción.
- Permite desarrollar múltiples features en paralelo sin que se interfieran.
- Facilita correcciones urgentes (`hotfix/*`) directamente desde `main` sin afectar el desarrollo activo.
- Hace el flujo de revisión más claro y ordenado mediante Pull Requests.

### Estructura de ramas

```
main        → código en producción (estable)
develop     → integración de features en desarrollo
feature/*   → nuevas funcionalidades (salen de develop)
hotfix/*    → correcciones urgentes (salen de main)
```

## Naming de ramas

- `feature/<nombre-corto>` para funcionalidades nuevas
- `hotfix/<nombre-corto>` para correcciones urgentes

Ejemplos:

- `feature/crear-readme`
- `hotfix/correccion-readme`

## Flujo de merge

Se sigue el flujo de GitFlow:

1. Las ramas `feature/*` nacen de `develop` y se integran por Pull Request a `develop`.
2. Las ramas `hotfix/*` nacen de `main` y se integran por Pull Request a `main`.
3. Luego de un hotfix, se sincroniza `main` hacia `develop` para mantener ambas ramas alineadas.

## Estrategia de revisión (Pull Requests)

- Cada cambio se integra mediante Pull Request.
- Antes de aprobar, se valida:
	- que el objetivo del cambio esté claro.
	- que no rompa el flujo existente.
	- que la acción de GitHub Actions esté en estado exitoso.
