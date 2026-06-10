import { render } from '@testing-library/svelte';
import { describe, expect, it } from 'vitest';

import AboutPage from './+page.svelte';

describe('About page', () => {
	it('should render the page heading', () => {
		const { container } = render(AboutPage);

		expect(container.querySelector('h1')).toBeDefined();
		expect(container.querySelector('h1')?.textContent).toBe('About Moshplaner');
	});

	it('should render the project description', () => {
		const { container } = render(AboutPage);
		const paragraphs = container.querySelectorAll('p');

		expect(paragraphs.length).toBeGreaterThan(0);
		const bodyText = [...paragraphs].map((p) => p.textContent).join(' ');
		expect(bodyText).toContain('SvelteKit frontend');
	});
});
