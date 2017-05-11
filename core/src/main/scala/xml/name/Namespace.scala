/*
 * Copyright 2014–2017 SlamData Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xml.name

import slamdata.Predef._

import monocle.macros.Lenses
import scalaz.{Order, Show}
import scalaz.std.tuple._

@Lenses
final case class Namespace(prefix: NSPrefix, uri: NSUri) {
  def apply(local: NCName): QName = QName.prefixed(prefix, local)
}

object Namespace extends NamespaceInstances

sealed abstract class NamespaceInstances {
  implicit val order: Order[Namespace] =
    Order.orderBy(ns => (ns.prefix, ns.uri))

  implicit val show: Show[Namespace] =
    Show.shows(ns => s"Namespace(${ns.prefix} = ${ns.uri})")
}
