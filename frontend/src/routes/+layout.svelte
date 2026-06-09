<script lang="ts">
	import './layout.css';
	let { children } = $props();
</script>

<div class="flex h-screen w-full bg-slate-900 text-slate-100 overflow-hidden font-sans">
	<!-- Sidebar -->
	<aside class="w-64 flex-shrink-0 bg-slate-800/80 backdrop-blur-lg border-r border-slate-700/50 shadow-2xl z-20 transition-all">
		<div class="h-16 flex items-center px-6 border-b border-slate-700/50">
			<h1 class="text-2xl font-black tracking-tight bg-gradient-to-r from-blue-400 via-indigo-400 to-purple-500 bg-clip-text text-transparent">Moshplaner</h1>
		</div>
		<nav class="p-4 space-y-2 mt-4">
			<a href="/" class="flex items-center gap-3 px-4 py-3 rounded-xl hover:bg-slate-700/60 hover:shadow-md transition-all active:scale-95 group">
				<div class="w-8 h-8 rounded-lg bg-blue-500/10 text-blue-400 flex items-center justify-center group-hover:bg-blue-500/20 transition-colors">
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/><polyline points="9 22 9 12 15 12 15 22"/></svg>
				</div>
				<span class="font-medium">Home</span>
			</a>
			<a href="/about" class="flex items-center gap-3 px-4 py-3 rounded-xl hover:bg-slate-700/60 hover:shadow-md transition-all active:scale-95 group">
				<div class="w-8 h-8 rounded-lg bg-purple-500/10 text-purple-400 flex items-center justify-center group-hover:bg-purple-500/20 transition-colors">
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><path d="M12 16v-4"/><path d="M12 8h.01"/></svg>
				</div>
				<span class="font-medium">About</span>
			</a>
		</nav>
	</aside>

	<!-- Main Content -->
	<main class="flex-1 flex flex-col relative overflow-hidden bg-gradient-to-br from-slate-900 to-slate-800">
		<!-- Top header/glass effect -->
		<header class="h-16 flex-shrink-0 bg-slate-900/40 backdrop-blur-xl border-b border-slate-700/30 z-10 flex items-center px-8">
			<!-- Header content can go here -->
		</header>
		
		<!-- Content area -->
		<div class="flex-1 overflow-y-auto p-8 relative">
			<!-- Decorative background blob -->
			<div class="absolute top-0 right-0 w-[500px] h-[500px] bg-blue-500/10 rounded-full blur-[100px] pointer-events-none"></div>
			<div class="absolute bottom-0 left-0 w-[600px] h-[600px] bg-purple-500/10 rounded-full blur-[120px] pointer-events-none"></div>

			<div class="max-w-5xl mx-auto relative z-10 animate-in fade-in slide-in-from-bottom-8 duration-700 ease-out fill-mode-both">
				{@render children()}
			</div>
		</div>
	</main>
</div>
