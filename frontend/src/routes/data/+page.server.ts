import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async () => {
	try {
		// Use native fetch — this runs server-side in the Docker network.
		// SvelteKit's wrapped fetch is for relative URLs; we need an absolute internal URL here.
		const backendUrl = process.env.BACKEND_URL ?? 'http://backend:8080/api/data';
		const apiKey = process.env.INTERNAL_API_KEY ?? 'secret-key-123';

		const response = await globalThis.fetch(backendUrl, {
			headers: {
				'X-Internal-Api-Key': apiKey
			}
		});

		if (!response.ok) {
			console.error(`Backend returned status: ${response.status}`);
			return { externalData: [], error: `Backend error: ${response.status}` };
		}

		const data = await response.json();
		return { externalData: data };
	} catch (e) {
		console.error('Error fetching from backend:', e);
		return { externalData: [], error: 'Backend unavailable' };
	}
};
