<script lang="ts">
	let { data } = $props();
</script>

<div class="space-y-6">
	<header class="mb-10">
		<h1 class="text-4xl font-extrabold tracking-tight text-white mb-2">External Data</h1>
		<p class="text-slate-400 text-lg">Data fetched securely from the Kotlin backend via internal API.</p>
	</header>

	{#if data.error}
		<div class="p-6 rounded-2xl bg-red-900/20 border border-red-500/40 text-red-300 flex items-center gap-4">
			<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
			<div>
				<p class="font-semibold">Could not reach the backend</p>
				<p class="text-sm text-red-400 mt-1">{data.error}</p>
			</div>
		</div>
	{/if}

	{#if data.externalData && data.externalData.length > 0}
		<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
			{#each data.externalData as item}
				<div class="p-6 rounded-2xl bg-slate-800/50 border border-slate-700/50 shadow-lg backdrop-blur-sm hover:border-emerald-500/50 transition-colors flex flex-col h-full">
					<h2 class="text-xl font-bold mb-3 text-emerald-400 line-clamp-2">{item.title}</h2>
					<p class="text-slate-300 leading-relaxed text-sm flex-1">{item.body}</p>
					<div class="mt-4 pt-4 border-t border-slate-700/50 flex justify-between items-center text-xs text-slate-500 font-mono">
						<span>ID: {item.id}</span>
						<span>User: {item.userId}</span>
					</div>
				</div>
			{/each}
		</div>
	{:else if !data.error}
		<div class="p-12 rounded-2xl bg-slate-800/50 border border-slate-700/50 shadow-lg backdrop-blur-sm text-center">
			<svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" class="mx-auto text-slate-500 mb-4"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"/><polyline points="17 8 12 3 7 8"/><line x1="12" y1="3" x2="12" y2="15"/></svg>
			<h3 class="text-xl font-semibold text-white mb-2">No data found</h3>
			<p class="text-slate-400">The backend might still be starting up or the database is empty.</p>
		</div>
	{/if}
</div>
