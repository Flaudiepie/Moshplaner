import { svelte } from '@sveltejs/vite-plugin-svelte';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { defineConfig } from 'vitest/config';

export default defineConfig(() => {
	const rootDirectory = fileURLToPath(new URL('.', import.meta.url));

	return {
		cacheDir: './vitest',
		plugins: [
			svelte({
				compilerOptions: {
					hmr: false,
					runes: true,
				},
			}),
		],
		resolve: {
			alias: {
				$lib: path.resolve(rootDirectory, './src/lib'),
			},
			conditions: ['browser'],
		},
		test: {
			globals: true,
			environment: 'jsdom',
			include: ['**/*.test.ts'],
			setupFiles: ['./scripts/test-setup/setup-testing-library.ts'],
			coverage: {
				provider: 'v8',
				thresholds: {
					lines: 80,
					branches: 80,
				},
			},
		},
	};
});
