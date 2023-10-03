<script>
  let count = 0
  const increment = () => {
    count++
  }
  $: doubled = count * 2
  $: console.log('doubled is', doubled)
  $: if (count > 5) {
    console.log('count is dangerously high!')
    count = 0
  }
</script>

<button on:click={increment}>
  count is {count}, doubled is {doubled}
</button>

{#if count > 3}
  <p>Count is too high!</p>
{:else if count > 0}
  <p>It's a good start.</p>
{:else}
  <p>Count is low</p>
{/if}
