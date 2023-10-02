<script>
    import PackageInfo from "./PackageInfo.svelte";

    const pkg = {
        name: 'svelte',
        version: 4,
        test: true
    }
</script>

<PackageInfo {...pkg} />