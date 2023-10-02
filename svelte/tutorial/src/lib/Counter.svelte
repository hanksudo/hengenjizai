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
