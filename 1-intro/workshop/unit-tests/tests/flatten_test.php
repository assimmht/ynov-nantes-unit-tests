<?php

use PHPUnit\Framework\TestCase;

require_once('./src/flatten.php');

class FlattenTest extends TestCase
{
    public function test_something()
    {
        $this->assertEquals(1337, 1337);
    }

    public function test_single_value()
    {
        $this->assertEquals(flatten([5]), [5]);
        $this->assertEquals(flatten([-5]), [-5]);
    }

    public function test_few_value()
    {
        $this->assertEquals(flatten([1, 2, 3]), [1, 2, 3]);
    }

    public function test_nested_once()
    {
        $this->assertEquals(flatten([5, [1]]), [5, 1]);
        $this->assertEquals(flatten([5, [1, [2]]]), [5, 1, 2]);
    }
}
